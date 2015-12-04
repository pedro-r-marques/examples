import jenkins.model.*
import hudson.util.Secret
import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.domains.*
import org.jenkinsci.plugins.plaincredentials.impl.*

domain = Domain.global()
provider = Jenkins.instance.getExtensionList("com.cloudbees.plugins.credentials.SystemCredentialsProvider")[0]
store = provider.getStore()

credentials = new StringCredentialsImpl(
    CredentialsScope.GLOBAL, "github-token",
    "github jenkins bot", Secret.fromString("{{ opencontrail_jenkins_bot_token }}"))

store.addCredentials(domain, credentials)

provider.save()
