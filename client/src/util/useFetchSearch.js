export const fetchSearchQuestion = async (keyword) => {
  // /api/v1/questions/search?kind=tag&keyword=python&page=5&size=10&sort=questionId%2Cdesc
  return fetch(`https://server.prestack41-25.kro.kr/api/v1/questions/search?kind=title&keyword=${keyword}`, {
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
