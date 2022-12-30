export const fetchVoteQuestion = async (id, vote) => {
  return fetch(`https://server.prestack41-25.kro.kr/api/v1/questions/votes/${id}?voteUp=${vote}`, {
    method: "PATCH",
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
        }
        throw Error("잘못된 요청");
      }
      return res.json();
    })
    .catch((err) => {
      console.error(err.message);
    });
};

export const fetchVoteAnswer = async (id, vote) => {
  return fetch(`https://server.prestack41-25.kro.kr/api/v1/answers/votes/${id}?voteUp=${vote}`, {
    method: "PATCH",
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
        }
        throw Error("잘못된 요청");
      }
      return res.json();
    })
    .catch((err) => {
      console.error(err.message);
    });
};
