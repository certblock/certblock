var IMP = window.IMP; // 생략 가능
IMP.init("imp33932512"); // 예: imp00000000

IMP.certification({
    /* ...중략... */
  }, function (rsp) { // callback
    if (rsp.success) { // 인증 성공 시
      // jQuery로 HTTP 요청
      jQuery.ajax({
        url: "{서버의 인증 정보를 받는 endpoint}", // 예: https://www.myservice.com/certifications
        method: "POST",
        headers: { "Content-Type": "application/json" },
        data: { imp_uid: rsp.imp_uid }
      });
    } else {
      alert("인증에 실패하였습니다. 에러 내용: " +  rsp.error_msg);
    }
  });