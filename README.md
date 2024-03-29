## VPNGuard

### NOTICE
I'm not the creator of this plugin, I just found this on bukkit and make it work in nukkit since It's useful for me.

### Description

VPNGuard will prevent players from joining your server behind any type of anonymizer (whether it be a VPN or a Proxy). This will effectively help reduce spammers/bots and Miscellaneous individuals from joining your server by kicking them if they have an IP address which belongs to a hosting organization.

VPNGuard uses a privately managed blocking list which is updated almost daily to Combat new threats.

### Commands

**/vpnguard notify** (Enable or Disable in-game notifications when a player tries to join with a VPN)
**/vpnguard report** (Generates a report with the list of players who have attempted to join your server with a VPN)
**/vpnguard clearcache** (Deletes all locally saved cache files)
**/vpnguard clearip <ip>** (Deletes the locally saved cache file for the specified IP address)
**/vpnguard country** (Allows you toggle between country blacklist or whitelist, add, remove, view or clear a country code from your country list)
**/vpnguard subnet** (Allows you subnet ban or unban a IP address range)
**/vpnguard lookup** <player or ip> (Allows you to search any IP address or Online Player in-game to view details)
**/vpnguard about** (Information about the plugin)

### Permissions

**vpnguard.permission.bypass** (Allows user or user group with this permission to bypass all checks)
**vpnguard.command.vpnguard** (Allows you to use /vpnguard) (Default:True)
**vpnguard.command.notify** (Allows you to use /vpnguard notify) (Default:OP)
**vpnguard.command.report** (Allows you to use /vpnguard report)
**vpnguard.command.clearcache** (Allows you to use /vpnguard clearcache) (Default:OP)
**vpnguard.command.clearip** (Allows you to use /vpnguard clearip) (Default:OP)
**vpnguard.command.country** (Allows you to use /vpnguard country) (Default:OP)
**vpnguard.command.subnet** (Allows you to use /vpnguard subnet) (Default:OP)
**vpnguard.command.lookup** (Allows you to use /vpnguard lookup) (Default:OP)
**vpnguard.command.about** (Allows you to use /vpnguard about) (Default:True)

### Configuration

[View Example config.yml by clicking here.](https://pastebin.com/09cLSEve)
- Variables for "commands" section
  - %p to represent the player connecting
  - %ip to grab the connecting player's IP Address
- Variables for "country-deny-message"
  - %p to represent the player connecting
  - %countryname to get the connecting players country name

**Additional Information:**
This plugin utilizes an external IP validation system, which means that the plugin makes a connection to tools.xioax.com and the following occurs:

When a player tries to join the server their IP is sent across the internet for validation
A query is formed and the IP is validated against a private database
The player is allowed to join based on the result from the query

### Preview

The following is a preview of a report generated by VPNGuard using the command /vpnguard report
![preview](http://i.imgur.com/GhLn91o.png)


### Frequently Asked Questions

**Q. How many players can the plugin look information up for?**
A. At the time of writing this the backend API provider has a 500 monthly request limit. Which means if you enable api-cache within config.yml you can get 500 monthly unique players and lookup information regarding them with no problem! Just to clarify once more, the plugin is free, however the API Backend this plugin uses is not.

**Q. What happens when the API monthly limit is reached?**
A. The API server will no longer provide information regarding additional/new users who attempt to join your server, from there those users would be either allowed to join your server without any sort of checking or they would be kicked, this is based on the value specified in the config.yml under bypass-check.

**Q. I have a server and more than 500 new users join per month is it possible I can get more monthly requests?**
A. Yes you can you would need to purchase an API key, at the time of writing this its only $5/mo

**Q. Where can I view details/purchase the API that is being used?**
A. You can visit the homepage by the API provider located at: http://bit.ly/host-blocker
