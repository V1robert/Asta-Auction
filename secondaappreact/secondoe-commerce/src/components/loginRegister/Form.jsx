import React from 'react';
import '../../App.css'
import { useForm } from 'react-hook-form';
import { Connect, useDispatch } from "react-redux";
import { setUser } from '../../Store/Slices/userSlices';

function Form() {

  const {register,handleSubmit,watch,formState:{errors}} = useForm({
    defaultValues:{
        name:'',
        surname:'',
        email:'',
        password:'',
        age:0,
        gender:""
    }
})
const dispatch = useDispatch()
const onSubmit = data => console.log(data)

const saveUser = data => dispatch(setUser(data))
               //chiave da vedere =name
console.log(watch('name'))  //per vedere il valore dell input


  return (
    <>

    <div className='ILPADDING'>
      <form noValidate onSubmit={handleSubmit(onSubmit,saveUser)}>
      <div className='row g-4'>
          <div className='col-12 col-sm-6 col-md-4 col-lg-6 col-xl-4'>
             <label form='ex-input-help' className='form-label"'>Inserisci Nome:</label>
             <input type='text' id='ex-input-help' name="ex-input-help" className='form-control' aria-describedby="ex-input-help-help"
              {...register('name', {required:true ,pattern: /^[A-Za-z]+$/i , maxLength:20 , minLength:3 })} />
           {errors.name && <span>Insert a valid name</span>}
           <div id="ex-input-help-help" className="form-text">Messaggio di aiuto</div>
        
           <label form='ex-input-help' className='form-label"'>Inserisci Cognome:</label>
             <input type='text' id='ex-input-help' name="ex-input-help" className='form-control' aria-describedby="ex-input-help-help"
              {...register('surname', {required:true ,pattern: /^[A-Za-z]+$/i , maxLength:20 , minLength:3 })} />
              {errors.surname && <span>This field is required</span>}
           <div id="ex-input-help-help" className="form-text">Messaggio di aiuto</div>

           <label form='ex-input-help' className='form-label"'>Inserisci Email:</label>
             <input type='email' id='ex-input-help' name="ex-input-help" className='form-control' aria-describedby="ex-input-help-help"
              {...register('email', {required:true ,pattern: /^[A-Za-z]+$/i , maxLength:20 , minLength:3 })} />
           {errors.email && <span>not a valid Email</span>}
           <div id="ex-input-help-help" className="form-text">Messaggio di aiuto</div>

           <label form='ex-input-help' className='form-label"'>Inserisci Password:</label>
             <input type='password' id='ex-input-help' name="ex-input-help" className='form-control' aria-describedby="ex-input-help-help"
              {...register('password', {required:true ,pattern: /^[A-Za-z]+$/i , maxLength:20 , minLength:6 })} />
            {errors.password && <span> Password must be longer then 6 </span>}
           <div id="ex-input-help-help" className="form-text">Messaggio di aiuto</div>

           <label form='ex-input-help' className='form-label"'>Inserisci Eta:</label>
             <input type='text' id='ex-input-help' name="ex-input-help" className='form-control' aria-describedby="ex-input-help-help"
              {...register('age', {required:true , min:18, max:99})} />
              {errors.age && <span> you must be over 18</span>}
           <div id="ex-input-help-help" className="form-text">Messaggio di aiuto</div>

           <label form='ex-input-help' className='form-label"'>sesso:</label>
           <select    {...register('gender', {required:true})}>
          <option value='male'>Male</option>
          <option value='female'>Female</option>
          <option value='Alien'>Alien</option>
          <option value='Toaster'>Toaster</option>
           </select>
           {errors.gender && <span>Select a gender</span>}

    <input   className='child'  type="submit" />
          </div> 
        </div>
        
         </form>
         </div>
      </>
  );
}

export default Form