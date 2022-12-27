export const fetchQuestionList = () => {
  return fetch("https://server.prestack41-25.kro.kr/api/v1/questions")
    .then((res) => {
      if (!res.ok) {
        throw Error("유효하지 않은 요청입니다.");
      }
      return res.json();
    })
    .catch((err) => {
      throw Error(err.message);
    });
};
// https://server.prestack41-25.kro.kr/api/v1/questions
