/**
 * DATA NULL CHECK (비어있거나 없는 데이터는 return false)
 * @param   val
 * @returns boolean
 */
const isEmpty = (val) => {
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
			/*headers: {'Content-Type': 'application/json;'},*/
            /*body: param*/
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