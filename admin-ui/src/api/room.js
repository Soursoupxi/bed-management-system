import axios from "axios";
import {instance} from "@/axios/request.js";

const listRoom = (query) => {
    return instance.get("/room/getAll",{
        params:query
    })
}

const listRoomBySearch = (query) => {
    return instance.get("/room/getBySearch",{
        params:query
    })
}


const delFloor = (id) => {
    return instance.get("/floor/del", {
        params: {
            id: id
        }
    })
}

const addFloor = (floor) => {
    return instance.post("/floor/add", {name: floor.name})
}

const listFloorById = (id) => {
    return instance.get("/floor/get", {
        params: {
            id: id
        }
    })
}

const editFloor = (floor) => {
    return instance.post("/floor/edit", {
        id: floor.id,
        name: floor.name
    })
}

export {
    listFloor, delFloor, addFloor,listFloorById,editFloor,listFloorBySearch
}