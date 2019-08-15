package dev.anullihate.vpnguard.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Scanner;

public final class SimpleCache {
    private int cache_time = 1;
    private String cache_path = "cache/";
    private String cache_extension = ".cache";

    public SimpleCache() {
    }

    public SimpleCache(String path) {
        this.set_cache_path(path);
    }

    public int get_cache_time() {
        return this.cache_time;
    }

    public String get_cache_path() {
        return this.cache_path;
    }

    public String get_cache_extension() {
        return this.cache_extension;
    }

    public void set_cache_time(int days) {
        this.cache_time = days;
    }

    public void set_cache_path(String path) {
        this.cache_path = path;
        if (!(new File(this.cache_path)).isDirectory()) {
            (new File(this.cache_path)).mkdirs();
        }

    }

    public void set_cache_extension(String ext) {
        this.cache_extension = ext;
    }

    public boolean is_cached(String label) {
        String filename = this.cache_path + this.safe_filename(label) + this.cache_extension;
        File file = new File(filename);
        long diff = (new Date()).getTime() - file.lastModified();
        return file.exists() && diff <= (long)this.cache_time * 24L * 60L * 60L * 1000L;
    }

    public String get_cache(String label) {
        if (this.is_cached(label)) {
            String filename = this.cache_path + this.safe_filename(label) + this.cache_extension;

            try {
                Scanner reader = (new Scanner(new File(filename))).useDelimiter("\\Z");
                Throwable var5 = null;

                String var6;
                try {
                    String data = reader.next();
                    reader.close();
                    var6 = data;
                } catch (Throwable var16) {
                    var5 = var16;
                    throw var16;
                } finally {
                    if (reader != null) {
                        if (var5 != null) {
                            try {
                                reader.close();
                            } catch (Throwable var15) {
                                var5.addSuppressed(var15);
                            }
                        } else {
                            reader.close();
                        }
                    }

                }

                return var6;
            } catch (FileNotFoundException var18) {
                return null;
            }
        } else {
            return null;
        }
    }

    public void set_cache(String label, String data) {
        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.cache_path + this.safe_filename(label) + this.cache_extension), "utf-8"));
            Throwable var4 = null;

            try {
                writer.write(data);
                writer.close();
            } catch (Throwable var14) {
                var4 = var14;
                throw var14;
            } finally {
                if (writer != null) {
                    if (var4 != null) {
                        try {
                            writer.close();
                        } catch (Throwable var13) {
                            var4.addSuppressed(var13);
                        }
                    } else {
                        writer.close();
                    }
                }

            }
        } catch (IOException var16) {
            System.out.println(var16.getMessage());
        }

    }

    public String get_data(String label, String url) throws MalformedURLException, IOException {
        String data = null;
        if (this.get_cache(label) != null) {
            data = this.get_cache(label);
            return data;
        } else {
            data = this.grab_url(url);
            this.set_cache(label, data);
            return data;
        }
    }

    public String grab_url(String url, int timeout, String userAgent) throws MalformedURLException, IOException {
        StringBuilder response = new StringBuilder();
        URL website = new URL(url);
        URLConnection connection = website.openConnection();
        connection.setConnectTimeout(timeout);
        connection.setRequestProperty("User-Agent", userAgent);
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        Throwable var8 = null;

        try {
            while((url = in.readLine()) != null) {
                response.append(url);
            }

            in.close();
        } catch (Throwable var17) {
            var8 = var17;
            throw var17;
        } finally {
            if (in != null) {
                if (var8 != null) {
                    try {
                        in.close();
                    } catch (Throwable var16) {
                        var8.addSuppressed(var16);
                    }
                } else {
                    in.close();
                }
            }

        }

        return response.toString();
    }

    public String grab_url(String url) throws MalformedURLException, IOException {
        StringBuilder response = new StringBuilder();
        URL website = new URL(url);
        URLConnection connection = website.openConnection();
        connection.setConnectTimeout(5000);
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        Throwable var6 = null;

        try {
            while((url = in.readLine()) != null) {
                response.append(url);
            }

            in.close();
        } catch (Throwable var15) {
            var6 = var15;
            throw var15;
        } finally {
            if (in != null) {
                if (var6 != null) {
                    try {
                        in.close();
                    } catch (Throwable var14) {
                        var6.addSuppressed(var14);
                    }
                } else {
                    in.close();
                }
            }

        }

        return response.toString();
    }

    public void clearCache() {
        File[] var1 = (new File(this.cache_path)).listFiles();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            File file = var1[var3];
            file.delete();
        }

    }

    public void clearCache(String label) throws FileNotFoundException {
        String filename = this.cache_path + this.safe_filename(label) + this.cache_extension;
        File file = new File(filename);
        if (file.exists()) {
            file.delete();
        } else {
            throw new FileNotFoundException();
        }
    }

    public int get_total_cached() {
        return (new File(this.cache_path)).listFiles().length;
    }

    private String safe_filename(String filename) {
        return filename.replaceAll("/[^0-9a-z\\.\\_\\-]/i", "");
    }
}
