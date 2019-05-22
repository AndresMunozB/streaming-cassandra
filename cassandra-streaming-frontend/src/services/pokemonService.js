import axios from "axios";

const instance = axios.create({
    baseURL: "http://localhost:3030",
    headers: {
        "Content-Type": "application/json"
    }
});

const pokemonService = {
    findAll(cb) {
        instance
            .get("/pokemones/")
            .then(res => {
                cb(res);
            })
            .catch(error => {
                // eslint-disable-next-line no-console
                console.log(error);
                return null;
            });
    },
    findByType(cb,type) {
        instance
            .get("/pokemones/type?type="+type)
            .then(res => {
                cb(res);
            })
            .catch(error => {
                // eslint-disable-next-line no-console
                console.log(error);
                return null;
            });
    },
    findByName(name,cb) {
        instance
            .get("/pokemones/name?name="+name)
            .then(res => {
                cb(res);
            })
            .catch(error => {
                // eslint-disable-next-line no-console
                console.log(error);
                return null;
            });
    }
};

export default pokemonService;
