import axios from "axios"

export const checkArchive = async (file) => {
    return await  axios.post(
        'http://localhost:8080/UploadWEB/rest/fileDaCaricareRest/upload',file
    )
}