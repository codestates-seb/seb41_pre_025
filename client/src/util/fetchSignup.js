export const fetchSignup = async (data) => {
  console.log(data);
  return fetch("https://server.prestack41-25.kro.kr/api/v1/members", {
    method: "POST",
    headers: {
      "Content-Type": "application/json;charset=UTF-8",
    },
    body: data,
  })
    .then((res) => {
      if (!res.ok) {
        if (res.status === 400) console.log("실패");
        if (res.status === 409) console.log("이미 존재하는 email 입니다.");
        throw Error("could not fetch the data for that resource");
      }

      if (res.status === 201) console.log("회원가입 성공");
      return res;
    })
    .catch((error) => {
      console.log(error.message);
    });
};
