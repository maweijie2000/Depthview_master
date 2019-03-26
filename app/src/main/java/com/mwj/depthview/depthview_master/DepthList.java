package com.mwj.depthview.depthview_master;

import java.util.List;

public class DepthList {
    private List<String[]> bids;
    private List<String[]> asks;

    public List<String[]> getBids() {
        return bids;
    }

    public void setBids(List<String[]> bids) {
        this.bids = bids;
    }

    public List<String[]> getAsks() {
        return asks;
    }

    public void setAsks(List<String[]> asks) {
        this.asks = asks;
    }
}
