package com.kingsoft.entities;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Presentation {
	Map<Short, String> relationMap;
	List<SlideMaster> sldMasterList;
	List<Slide> sldList;

	public String getRelation(Short rId) {
		return relationMap.get(rId);
	}

	public void setRelationMap(Map<Short, String> relationMap) {
		this.relationMap = relationMap;
	}

	public Iterator<SlideMaster> getSldMasterListIterator() {
		return sldMasterList.iterator();
	}
	
	public int getSldMasterListSize(){
		return sldMasterList.size();
	}

	public void setSldMasterList(List<SlideMaster> sldMasterList) {
		this.sldMasterList = sldMasterList;
	}

	public Iterator<Slide> getSldListIterator() {
		return sldList.iterator();
	}
	
	public int getSldListSize(){
		return sldList.size();
	}

	public void setSldList(List<Slide> sldList) {
		this.sldList = sldList;
	}

	
}
