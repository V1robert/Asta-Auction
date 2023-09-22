import axios from "axios"

export const inviaZip= async(FileCaricato) => {
    return await axios.post(
        'http://localhost:8080/UploadWEB/rest/fileDaCaricareRest/upload',FileCaricato
    )
    
}