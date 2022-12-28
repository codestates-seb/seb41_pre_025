export const fetchQuestionList = async (questionId = 0) => {
  return fetch(
    `https://server.prestack41-25.kro.kr/api/v1/questions${
      questionId !== 0 ? `/${questionId}` : ''
    }`,
    {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json;charset=UTF-8',
        Accept: 'application/json',
      },
    }
  )
    .then((res) => {
      if (!res.ok) {
        throw Error('nonono');
      }
      return res.json();
    })
    .catch((err) => {
      console.error(err.message);
    });
};
