export function authHeader() {
    // return authorization header with jwt token
    let jwt = localStorage.getItem("jwt");
    if (jwt) {
        return {headers:{ 'Authorization': 'Bearer ' + jwt }};
    } else {
        return {};
    }
}
