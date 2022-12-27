import { useState, useEffect } from "react";
import { useParams } from "react-router";

const useFetch = (url) => {
	const [questions, setQuestions] = useState(null);
	// const [isPending, setIsPending] = useState(true);
	// const [isLike, setIsLike] = useState(false);
	const [error, setError] = useState(null);
	const param = useParams();

	useEffect(() => {
		// setTimeout(() => {
		fetch(url)
			.then((res) => {
				if (!res.ok) {
					throw Error("could not fetch the data for that resource");
				}
				return res.json();
			})
			.then((data) => {
				setQuestions(data);
				setError(null);
				// setIsLike(data.heart);
				// setIsPending(false);
			})
			.catch((err) => {
				// setIsPending(false);
				setError(err.message);
			});
		// }, 1000);
	}, []);

	return [questions];
};

export default useFetch;

// import { useState, useEffect } from "react";
// import { useParams } from "react-router-dom";

// const useFetch = (url) => {
// 	/* useState를 이용하여 data, isPending, error를 정의하세요. */

// 	/* useFetch 안의 중심 로직을 작성해주세요. */

// 	const [blogs, setBlogs] = useState(null);
// 	const [isPending, setIsPending] = useState(true);
// 	const [isLike, setIsLike] = useState(false);
// 	const [error, setError] = useState(null);
// 	const param = useParams();

// 	/* get 메소드를 통해 데이터를 받아오는 useEffect hook은 컴포넌트 내 여기저기 존재하고 있습니다. */
// 	/* 해당 hook은 반복이 되는 부분이 있으므로 어떻게 custom hook으로 만들 수 있을 지 고민해봅시다. */
// 	/* util 폴더 내에 존재하는 useFetch에 여러분의 custom hook을 작성해주세요. */
// 	useEffect(() => {
// 		setTimeout(() => {
// 			fetch(url)
// 				.then((res) => {
// 					if (!res.ok) {
// 						throw Error("could not fetch the data for that resource");
// 					}
// 					return res.json();
// 				})
// 				.then((data) => {
// 					setIsLike(data.heart);
// 					setIsPending(false);
// 					setBlogs(data);
// 					setError(null);
// 				})
// 				.catch((err) => {
// 					setIsPending(false);
// 					setError(err.message);
// 				});
// 		}, 1000);
// 	}, [param]);

// 	return [blogs, isPending, error, isLike, setIsLike];
// 	/* return 문을 작성해주세요. */
// };

// export default useFetch;
