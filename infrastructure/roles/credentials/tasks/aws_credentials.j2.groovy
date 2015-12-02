import jenkins.model.*
import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.domains.*
import com.cloudbees.jenkins.plugins.awscredentials.*

domain = Domain.global()
provider = Jenkins.instance.getExtensionList("com.cloudbees.plugins.credentials.SystemCredentialsProvider")[0]
store = provider.getStore()

// list = store.getCredentials(domain)
// exists = list.any{ it instanceof AWSCredentialsImpl && ((AWSCredentialsImpl) it).getId() == "k8s-provisioner" }

credentials = new AWSCredentialsImpl(CredentialsScope.GLOBAL, "k8s-provisioner", "{{ aws_access_key_id }}", "{{ aws_secret_access_key }}", "k8s provisioner")
store.addCredentials(domain, credentials)
provider.save()
