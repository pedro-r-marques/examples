import jenkins.model.*
import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.domains.*
import com.cloudbees.jenkins.plugins.sshcredentials.impl.BasicSSHUserPrivateKey

domain = Domain.global()
provider = Jenkins.instance.getExtensionList("com.cloudbees.plugins.credentials.SystemCredentialsProvider")[0]
store = provider.getStore()

key = new BasicSSHUserPrivateKey.DirectEntryPrivateKeySource("{{ k8s_ssh_key }}")
credentials = new BasicSSHUserPrivateKey(CredentialsScope.GLOBAL, "k8s", "{{ ansible_ssh_user }}", key, "", "k8s ssh key")
store.addCredentials(domain, credentials)
provider.save()
