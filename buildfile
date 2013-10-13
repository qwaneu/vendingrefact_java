VERSION_NUMBER = "1.0.0"
GROUP = "Vending Machine Java"
COPYRIGHT = "(c) 2013 QWAN - Quality Without a Name"

repositories.remote << "http://uk.maven.org/maven2"

desc "The Vending Machine Java project"
define "vending_machine_java" do
  project.version = VERSION_NUMBER
  project.group = GROUP
  manifest["Implementation-Vendor"] = COPYRIGHT

  test.with Dir['lib/*.jar']
end
