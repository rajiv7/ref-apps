package com.sanlark.vendormgmnt.logicLayer;

import com.sanlark.vendormgmnt.pojo.dtls.RequestStatus;

public class RequestStatusUtil {

	public static boolean isUpdateAllowed(RequestStatus requestStatus) {
		boolean result = true;
		switch (requestStatus) {
			case VENDOR_SELECTED:
			case WORK_COMPLETED:
			case CLOSED:{
				result = false;
			}
			default:
				break;
		}
		return result;
	}
	
	public static boolean isSendProposalAllowed(RequestStatus requestStatus) {
		boolean result = false;
		switch (requestStatus) {
			case DECLINED_BY_GTEC:
			case SUBMITTED_TO_VENDORS:{
				result = true;
			}
			default:
				break;
		}
		return result;
	}

	public static boolean isGtecAllowed(RequestStatus requestStatus) {
		boolean result = false;
		switch (requestStatus) {
			case SUBMITTED_TO_GTEC:
			case RE_SUBMITTED_TO_GTEC:{
				result = true;
			}
			default:
				break;
		}
		return result;
	}
	
	public static boolean isGtecActionAllowed(RequestStatus requestStatus) {
		boolean result = false;
		switch (requestStatus) {
			case ACCEPTED_BY_GTEC:
			case DECLINED_BY_GTEC:{
				result = true;
			}
			default:
				break;
		}
		return result;
	}

	public static boolean isProcAllowed(RequestStatus requestStatus) {
		boolean result = false;
		switch (requestStatus) {
			case ACCEPTED_BY_GTEC:
			case VENDOR_SELECTED:
			case SUBMITTED_TO_VENDORS:{
				result = true;
			}
			default:
				break;
		}
		return result;
	}

	public static boolean isProcCanUpdate(RequestStatus requestStatus) {
		return requestStatus == RequestStatus.SUBMITTED_TO_VENDORS;
	}
	public static boolean isCompleteAllowed(RequestStatus requestStatus) {
		boolean result = false;
		switch (requestStatus) {
			case ACCEPTED_BY_GTEC:
			case VENDOR_SELECTED:{
				result = true;
			}
			default:
				break;
		}
		return result;
	}
	
	public static boolean isVendorSelected(RequestStatus requestStatus) {
		boolean result = false;
		switch(requestStatus){
			case ACCEPTED_BY_GTEC:
			case VENDOR_SELECTED:
			case WORK_COMPLETED:
			case CLOSED:{
				result = true;
				break;
			}
			default:{
				break;
			}
		}
		return result;
	}
}
