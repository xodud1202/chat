const URL_MAIN = "/";
const URL_JOIN = "/customer/join/{custId}";
const URL_JOIN_FORM = "/customer/join/form";
const URL_LOGIN_FORM = "/customer/login/form";
const URL_FRIEND_LIST_FORM = "/friend/list/form";

const POST = "POST";
const GET = "GET";
const PUT = "PUT";
const DELETE = "DELETE";


/**
 * DATA NULL CHECK (비어있거나 없는 데이터는 return false)
 * @param   val
 * @returns boolean
 */
const isNull = (val) => {
    return val === undefined || val == null || val.length <= 0;
}

/**
 * ajax 통신 처리(param, callbackFn 없이 사용 가능)
 * @param method API 메소드 종류 (GET, POST, PUT, DELETE 등)
 * @param url API 통신 URL
 * @param param API 송신 PARAMETER
 * @param callbackFn 통신 후 CALLBACK FUNCTION
 */
const ltyJsonFetch = (method, url, param, callbackFn) => {
    let headers;
    if(method === "GET") {
        headers = {method: method};
    } else {
        headers = {
            method: method,
            headers: {'Content-Type': 'application/json;', "Accept": 'application/json'},
			body: JSON.stringify(param)
        }
    }

	fetch(url, headers).then(res => res.json()).then(result => {
		if (typeof(callbackFn) == "function") {
			callbackFn.call(this, result);
		}
	}).catch((error) => {
		console.error("Error : ", error);
	});
}

/**
 * 특수문자 존재 여부 체크 (특수문자가 존재하면 return true)
 * @param str 검색할 문자열
 */
const fnCheckSpecialChar = (str) => {
	return /[^a-zA-Z0-9]/gi.test(str);
}

/**
 * 영문입력체크 (영문 존재시 return true)
 * @param str 검색할 문자열
 */
const fnValidtaionEng = (str) => {
	return /[a-zA-Z]/ig.test(str);
};

/**
 * 숫자포함체크 (숫자 포함시 return true)
 * @param str 검색할 문자열
 */
const fnValidtaionNumber = (str) => {
	return /[0-9]/g.test(str);
};

/**
 * 특수문자포함체크 (특수문자 포함시 Return true)
 * @param str 검색할 문자열
 */
const fnValidtaionSpecialChar = (str) => {
	return /[!@#$%^&*()?_~]/g.test(str);
};

/**
 * 이메일 유효성 체크 (정상 return true)
 * @param email - 이메일
 */
const fnCheckValidationEmail = (email) => {
	return /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/.test(email);
};