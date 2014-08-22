package com.kingsoft.entities;

public class NvSpPr {// Non-Visual Properties for a Shape
	// Placeholder Shape Type
	public enum PhType {
		body, chart, clipArt, ctrTitle, dgm, dt, ftr, hdr, media, obj, pic, sldImg, sldNum, subTitle, tbl, title
	};

	// cNvPr
	private String cNvPr_name;
	private int cNvPr_id;
	// cNvSpPr
	private short spLocks_noGrp;
	// nvPr
	private PhType nvPr_ph; // Placeholder Shape

	public String getcNvPr_name() {
		return cNvPr_name;
	}

	public short getSpLocks_noGrp() {
		return spLocks_noGrp;
	}

	public void setSpLocks_noGrp(short spLocks_noGrp) {
		this.spLocks_noGrp = spLocks_noGrp;
	}

	public void setcNvPr_name(String cNvPr_name) {
		this.cNvPr_name = cNvPr_name;
	}

	public int getcNvPr_id() {
		return cNvPr_id;
	}

	public void setcNvPr_id(int cNvPr_id) {
		this.cNvPr_id = cNvPr_id;
	}

	public PhType getNvPr_ph() {
		return nvPr_ph;
	}

	public void setNvPr_ph(PhType nvPr_ph) {
		this.nvPr_ph = nvPr_ph;
	}

}
