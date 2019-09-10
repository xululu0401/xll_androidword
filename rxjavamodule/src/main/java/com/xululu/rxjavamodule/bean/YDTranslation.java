package com.xululu.rxjavamodule.bean;

import java.util.List;

/**
 * Author: llxu4
 * Time: 2019-08-04 23:49
 */
public class YDTranslation {

    private String type;
    private int errorCode;
    private int elapsedTime;
    private List<List<TranslateResult>> translateResult;
    public class TranslateResult{
        private String src;
        private String tgt;

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getTgt() {
            return tgt;
        }

        public void setTgt(String tgt) {
            this.tgt = tgt;
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(int elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public List<List<TranslateResult>> getTranslateResult() {
        return translateResult;
    }

    public void setTranslateResult(List<List<TranslateResult>> translateResult) {
        this.translateResult = translateResult;
    }
}
