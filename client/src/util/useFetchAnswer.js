export const fetchAnswersList = async (answerId = 0) => {
  return fetch(`https://server.prestack41-25.kro.kr/api/v1/answers${answerId !== 0 ? `/${answerId}` : ""}`, {
    method: "GET",
    headers: {
      "Content-Type": "application/json;charset=UTF-8",
      Accept: "application/json",
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

export const fetchMyAnswersList = async () => {
  return fetch(`https://server.prestack41-25.kro.kr/api/v1/answers/myanswers`, {
    method: "GET",
    headers: {
      "Content-Type": "application/json;charset=UTF-8",
      Accept: "application/json",
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

export const fetchCreateAnswer = async (id, data) => {
  return fetch(`https://server.prestack41-25.kro.kr/api/v1/answers?questionId=${id}`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json;charset=UTF-8",
      Accept: "application/json",
      Authorization: sessionStorage.getItem("access_token"),
    },
    body: JSON.stringify(data),
  })
    .then((res) => {
      if (!res.ok) {
        if (res.status === 401) {
          alert("로그인이 필요합니다");
        } else if (res.status === 409) {
          alert("권한이 없습니다");
        }
        throw Error("잘못된 요청");
      }
      return res.json();
    })
    .catch((err) => {
      console.error(err.message);
    });
};

export const fetchModifyAnswer = async (id, data) => {
  return fetch(`https://server.prestack41-25.kro.kr/api/v1/answers/${id}`, {
    method: "PATCH",
    headers: {
      "Content-Type": "application/json;charset=UTF-8",
      Accept: "application/json",
      Authorization: sessionStorage.getItem("access_token"),
    },
    body: JSON.stringify(data),
  })
    .then((res) => {
      if (!res.ok) {
        if (res.status === 401) {
          alert("로그인이 필요합니다");
        } else if (res.status === 403) {
          alert("권한이 없습니다");
        } else if (res.status === 409) {
          alert("권한이 없습니다");
        }
        throw Error("잘못된 요청");
      }
      return res.json();
    })
    .catch((err) => {
      console.error(err.message);
    });
};

export const fetchDeleteAnswer = async (id) => {
  return fetch(`https://server.prestack41-25.kro.kr/api/v1/answers/${id}`, {
    method: "DELETE",
    headers: {
      "Content-Type": "application/json;charset=UTF-8",
      Accept: "application/json",
      Authorization: sessionStorage.getItem("access_token"),
    },
  })
    .then((res) => {
      if (!res.ok) {
        if (res.status === 401) {
          alert("로그인이 필요합니다");
        } else if (res.status === 403) {
          alert("권한이 없습니다");
        } else if (res.status === 409) {
          alert("권한이 없습니다");
        }
        throw Error("잘못된 요청");
      }
      return res;
    })
    .catch((err) => {
      console.error(err.message);
    });
};
