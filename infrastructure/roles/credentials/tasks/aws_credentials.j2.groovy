import jenkins.model.*
import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.domains.*
import com.cloudbees.plugins.credentials.impl.*

domain = Domain.global()
provider = Jenkins.instance.getExtensionList("com.cloudbees.plugins.credentials.SystemCredentialsProvider")[0]
store = provider.getStore()

credentials = new UsernamePasswordCredentialsImpl(
    CredentialsScope.GLOBAL, "k8s-provisioner", "k8s provisioner",
    "{{ aws_access_key_id }}",
    "{{ aws_secret_access_key }}")
store.addCredentials(domain, credentials)
provider.save()
