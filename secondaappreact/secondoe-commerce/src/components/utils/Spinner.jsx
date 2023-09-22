import React from 'react'
import {useTranslation} from "react-i18next";

function Spinner(){
    const {t}=useTranslation("translations")

    return(
        <div className="spinner spinner-4x" role="status">
            <div className="spinner-border text-primary" />
            <span className="px-4">{t('spinner.loading')}</span>
        </div>
    )
}

export default Spinner