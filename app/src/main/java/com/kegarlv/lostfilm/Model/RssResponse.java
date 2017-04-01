package com.kegarlv.lostfilm.Model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by ivan on 04.02.17.
 */
@Root(name = "rss")
public class RssResponse {

    @Attribute(required = false)
    String version;

    @Element(name = "channel")
    Channel channel;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public RssResponse() {
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

}
