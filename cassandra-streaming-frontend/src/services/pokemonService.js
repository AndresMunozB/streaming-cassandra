import axios from "axios";

const instance = axios.create({
    baseURL: "http://34.238.53.42:3030",
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
                console.log('Error en la consulta: '+ error);
                return null;
            });
    },
    getCount(cb) {
        instance
            .get("/pokemones/count")
            .then(res => {
                cb(res);
            })
            .catch(error => {
                // eslint-disable-next-line no-console
                console.log('Error en la consulta: '+ error);
                return null;
            });
    },
    findByType(type,cb) {
        instance
            .get("/pokemones/type?type="+type)
            .then(res => {
                cb(res);
            })
            .catch(error => {
                // eslint-disable-next-line no-console
                console.log('Error en la consulta: '+ error);
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
                console.log('Error en la consulta: '+ error);
                return null;
            });
    }
};

export default pokemonService;
