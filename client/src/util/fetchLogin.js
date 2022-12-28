export const fetchLogin = async (data) => {
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
  return fetch("https://server.prestack41-25.kro.kr/api/v1/members", {
    method: "GET",
    headers: {
      "Content-Type": "application/json;charset=UTF-8",
      Authorization: sessionStorage.getItem("access_token"),
    },
  })
    .then((res) => {
      if (!res.ok) {
        throw Error("nonono");
      }
      return res.json();
    })
    .catch((err) => {
      console.error(err.message);
    });
};

export const checkLogin = async () => {
  return await fetchMemberInfo().then((data) => {
    if (!data) {
      window.location.href = "/login";
      console.log("Please login");
    }
  });
};
