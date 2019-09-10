package com.xululu.rxjavamodule.bean;

/**
 * Author: pipilu
 * Time: 2019-08-04 13:35
 */
public class Translation {

    private int status;
    private Content content;
    public class Content {
        private String from;
        private String to;
        private String vendor;
        private String out;
        private int eroor_no;

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getVendor() {
            return vendor;
        }

        public void setVendor(String vendor) {
            this.vendor = vendor;
        }

        public String getOut() {
            return out;
        }

        public void setOut(String out) {
            this.out = out;
        }

        public int getEroor_no() {
            return eroor_no;
        }

        public void setEroor_no(int eroor_no) {
            this.eroor_no = eroor_no;
        }
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
}
