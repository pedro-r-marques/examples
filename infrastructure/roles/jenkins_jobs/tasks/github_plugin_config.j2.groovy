import org.jenkinsci.plugins.github.config.*

servers = [new GitHubServerConfig('github-token')]
config = new GitHubPluginConfig(servers)
config.save()
