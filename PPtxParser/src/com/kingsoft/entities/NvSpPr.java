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
	private PhType nvPr_ph_type; // Placeholder Shape
	private int nvPr_ph_idx;// Placeholder Index

	public int getNvPrPhIdx() {
		return nvPr_ph_idx;
	}

	public void setNvPrPhIdx(int nvPr_ph_idx) {
		this.nvPr_ph_idx = nvPr_ph_idx;
	}

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

	public int getcNvPrId() {
		return cNvPr_id;
	}

	public void setcNvPrId(int cNvPr_id) {
		this.cNvPr_id = cNvPr_id;
	}

	public PhType getNvPrPhType() {
		return nvPr_ph_type;
	}

	public void setNvPrPhType(PhType nvPr_ph_type) {
		this.nvPr_ph_type = nvPr_ph_type;
	}

}
