export function authHeader() {
    // return authorization header with jwt token
    const jwt = localStorage.getItem("jwt");
    if (jwt) {
        return {headers:{ 'Authorization': 'Bearer ' + jwt }};
    } else {
        return {};
    }
}
