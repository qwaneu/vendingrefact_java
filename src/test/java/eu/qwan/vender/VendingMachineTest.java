package eu.qwan.vender;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class VendingMachineTest {
	private final VendingMachine machine = new VendingMachine();

	@Test
	public void Testchoiceless_machine_delivers_nothing() {
		assertEquals(Can.NONE, machine.deliver(Choice.COLA));
		assertEquals(Can.NONE, machine.deliver(Choice.FANTA));
	}

	@Test
	public void Testdelivers_can_of_choice() {
		machine.configure(Choice.COLA, Can.COLA, 10);
		machine.configure(Choice.FANTA, Can.FANTA, 10);
		machine.configure(Choice.SPRITE, Can.SPRITE, 10);
		assertEquals(Can.COLA, machine.deliver(Choice.COLA));
		assertEquals(Can.FANTA, machine.deliver(Choice.FANTA));
		assertEquals(Can.SPRITE, machine.deliver(Choice.SPRITE));
	}

	@Test
	public void Testdelivers_nothing_when_making_invalid_choice() {
		machine.configure(Choice.COLA, Can.COLA, 10);
		machine.configure(Choice.FANTA, Can.FANTA, 10);
		machine.configure(Choice.SPRITE, Can.SPRITE, 10);
		assertEquals(Can.NONE, machine.deliver(Choice.BEER));
	}

	@Test
	public void Testdelivers_nothing_when_not_paid() {
		machine.configure(Choice.FANTA, Can.FANTA, 10, 2);
		machine.configure(Choice.SPRITE, Can.SPRITE, 10, 1);

		assertEquals(Can.NONE, machine.deliver(Choice.FANTA));
	}

	@Test
	public void Testdelivers_fanta_when_paid() {
		machine.configure(Choice.SPRITE, Can.SPRITE, 10, 1);
		machine.configure(Choice.FANTA, Can.FANTA, 10, 2);

		machine.setValue(2);
		assertEquals(Can.FANTA, machine.deliver(Choice.FANTA));
		assertEquals(Can.NONE, machine.deliver(Choice.SPRITE));
	}

	@Test
	public void Testdelivers_sprite_when_paid() {
		machine.configure(Choice.SPRITE, Can.SPRITE, 10, 1);
		machine.configure(Choice.FANTA, Can.FANTA, 10, 2);

		machine.setValue(2);
		assertEquals(Can.SPRITE, machine.deliver(Choice.SPRITE));
		assertEquals(Can.SPRITE, machine.deliver(Choice.SPRITE));
		assertEquals(Can.NONE, machine.deliver(Choice.SPRITE));
	}

	@Test
	public void Testadd_payments() {
		machine.configure(Choice.SPRITE, Can.SPRITE, 10, 1);
		machine.configure(Choice.FANTA, Can.FANTA, 10, 2);

		machine.setValue(1);
		machine.setValue(1);
		assertEquals(Can.SPRITE, machine.deliver(Choice.SPRITE));
		assertEquals(Can.SPRITE, machine.deliver(Choice.SPRITE));
		assertEquals(Can.NONE, machine.deliver(Choice.SPRITE));
	}

	@Test
	public void Testreturns_change() {
		machine.configure(Choice.SPRITE, Can.SPRITE, 10, 1);
		machine.setValue(2);
		assertEquals(2, machine.getChange());
		assertEquals(0, machine.getChange());
	}

	@Test
	public void Teststock() {
		machine.configure(Choice.SPRITE, Can.SPRITE, 1, 0);
		assertEquals(Can.SPRITE, machine.deliver(Choice.SPRITE));
		assertEquals(Can.NONE, machine.deliver(Choice.SPRITE));
	}

	@Test
	public void Testadd_stock() {
		machine.configure(Choice.SPRITE, Can.SPRITE, 1, 0);
		machine.configure(Choice.SPRITE, Can.SPRITE, 1, 0);
		assertEquals(Can.SPRITE, machine.deliver(Choice.SPRITE));
		assertEquals(Can.SPRITE, machine.deliver(Choice.SPRITE));
		assertEquals(Can.NONE, machine.deliver(Choice.SPRITE));
	}

	@Test
	public void Testcheckout_chip_if_chipknip_inserted() {
		machine.configure(Choice.SPRITE, Can.SPRITE, 1, 1);
		Card chip = new Card(10);
		machine.insertCard(chip);
		assertEquals(Can.SPRITE, machine.deliver(Choice.SPRITE));
		assertEquals(9, chip.credits);
	}

	@Test
	public void Testcheckout_chip_empty() {
		machine.configure(Choice.SPRITE, Can.SPRITE, 1, 1);
		Card chip = new Card(0);
		machine.insertCard(chip);
		assertEquals(Can.NONE, machine.deliver(Choice.SPRITE));
		assertEquals(0, chip.credits);
	}
}
