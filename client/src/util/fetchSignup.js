export const fetchSignup = async (data) => {
  return fetch(`/`, {
    method: "POST",
    header: { "Content-Type": "" },
    body: data,
  })
    .then((res) => {
      if (!res.ok) {
        if (res.status === 400) console.log("Email already exists");
        throw Error("could not fetch the data for that resource");
      }

      if (res.status === 201) console.log("Congratulations! Your account has been successfully created!");
      return res;
    })
    .catch((error) => {
      console.log(error.message);
    });
};
