package dev.anullihate.vpnguard.subnet;

import dev.anullihate.vpnguard.VPNGuard;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;

public class SubnetManager {
    private VPNGuard plugin;
    private String ip;

    public SubnetManager(VPNGuard plugin, String ip) {
        this.ip = ip;
        this.plugin = plugin;
    }

    public boolean isBanned() {
        ArrayList<String> subnets = (ArrayList)this.plugin.stream.data.get("subnets");
        boolean banned = false;
        Iterator var3 = subnets.iterator();

        while(var3.hasNext()) {
            String subnet = (String)var3.next();

            try {
                CIDRUtils cidrUtils = new CIDRUtils(subnet);
                long IPMin = this.ipToLong(InetAddress.getByName(cidrUtils.getNetworkAddress()));
                long IPMax = this.ipToLong(InetAddress.getByName(cidrUtils.getBroadcastAddress()));
                long IP = this.ipToLong(InetAddress.getByName(this.ip));
                banned = IP <= IPMax && IP >= IPMin;
                if (banned) {
                    break;
                }
            } catch (UnknownHostException var12) {
                banned = false;
            }
        }

        return banned;
    }

    private long ipToLong(InetAddress ip) {
        byte[] octets = ip.getAddress();
        long result = 0L;
        byte[] var5 = octets;
        int var6 = octets.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            byte octet = var5[var7];
            result <<= 8;
            result |= (long)(octet & 255);
        }

        return result;
    }
}
