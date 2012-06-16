package com.fdm.routeplanner.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("journey")
@Scope("request")
class Journey implements _Journey{

	private final List<String> message;
	private int lowestZone;
	
	/**
	 * @param messages
	 */
	@Autowired
	Journey(@Value("#{arrayList}") List<String> messages) {
		this.message = messages;
	}

	public List<String> getMessage() {
		return message;
	}

	public final void addMessage(String message) {
		this.message.add(message);	
	}
	
	public final void clearJourney() {
		message.clear();	
	}

	public void setLowestZone(int lowestZone) {
		this.lowestZone = lowestZone;
	}

	public int getLowestZone() {
		return lowestZone;
	}
}
