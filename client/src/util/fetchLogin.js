export const fetchLogin = async (data) => {
  console.log(data);
  return fetch("https://server.prestack41-25.kro.kr/api/v1/auth/login", {
    method: "POST",
    headers: {
      "Content-Type": "application/json;charset=UTF-8",
    },
    body: data,
  })
    .then((res) => {
      if (!res.ok) {
        console.log(res);
        if (res.status === 401) console.log("Wrong Email or Password");
        throw Error("could not fetch the data for that resource");
      }
      if (res.status === 200) {
        console.log("Login Success!");

        const accessToken = res.headers.get("Authorization");
        const refreshToken = res.headers.get("RefreshToken");
        sessionStorage.setItem("access_token", accessToken);
        sessionStorage.setItem("refresh_token", refreshToken);
      }
      return res;
    })
    .catch((err) => {
      console.error(err.message);
    });
};

export const fetchMemberInfo = async () => {
  return fetch("https://server.prestack41-25.kro.kr/api/members?_csrf=4c4cfad8-db05-4b17-afa1-f6e769563af5", {
    method: "GET",
    headers: {
      "Content-Type": "application/json;charset=UTF-8",
      Authorization: "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiZXhwIjoxNjcxNjg5Nzg4fQ.eFeEyh5F5ilhUfK28DzIxNPscqrlo5d9kNcOZYgbsUs",
    },
  })
    .then((res) => {
      console.log(res);
      if (!res.ok) {
        throw Error("nonono");
      }
      return res.json();
    })
    .catch((err) => {
      console.error(err.message);
    });
};
