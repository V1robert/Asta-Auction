import React from 'react'
import Spinner from './Spinner'

function SpinnerModal()  {
    return (
        <>
        <div className='modal-dialog modal-dialog-centered modal-sm'>
            <div className='modal-content'>
                <div className='modal-body ' role='status'>
                    <Spinner />
                </div>
            </div>
        </div>
        </>
    )
}
export default SpinnerModal
