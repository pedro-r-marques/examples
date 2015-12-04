import hudson.security.*
import jenkins.model.*

instance = Jenkins.getInstance()

User user

realm = instance.getSecurityRealm()

if (realm == null || realm.class != hudson.security.HudsonPrivateSecurityRealm) {
    realm = new HudsonPrivateSecurityRealm(false)
    instance.setSecurityRealm(realm)
    user = realm.createAccount("admin", "{{ jenkins_passwd }}")
} else {
    user = realm.getUser("admin")
}

String public_key = new File("/var/lib/jenkins/jenkins.key.pub").text

keys = new org.jenkinsci.main.modules.cli.auth.ssh.UserPropertyImpl(public_key)
user.addProperty(keys)
strategy = instance.getAuthorizationStrategy()

if (strategy == null || strategy.class != hudson.security.GlobalMatrixAuthorizationStrategy) {
    strategy = new GlobalMatrixAuthorizationStrategy()
    strategy.add(Jenkins.ADMINISTER, "admin")
    strategy.add(Permission.READ, ACL.ANONYMOUS_USERNAME)
    instance.setAuthorizationStrategy(strategy)
}

instance.save();
