package com.gewara.service;

public interface InitDataService {
	void startInit(String info, Runnable call);
	void delayInit(String info, Runnable call, long delayMills);
}
