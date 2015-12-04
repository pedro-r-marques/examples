import org.jenkinsci.plugins.github.config.*
import java.net.URL

url = new URL('http', "{{ ansible_ssh_host }}", 8080, '/github-webhook/')
servers = [new GitHubServerConfig('github-token')]
config = new GitHubPluginConfig()
config.setConfigs(servers)
config.setOverrideHookUrl(true)
config.setHookUrl(url)
config.save()
