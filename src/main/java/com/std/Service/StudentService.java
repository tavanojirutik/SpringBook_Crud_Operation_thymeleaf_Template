package com.std.Service;

import java.util.List;

import com.std.Dto.StudentDto;
import com.std.Entity.StudentEntity;

public interface StudentService {

//	============Save Data===========
	public void saveData(StudentDto studentDto);
	
	StudentEntity DtoToEntity(StudentDto studentDto);
	StudentDto EntityToDto (StudentEntity studentEntity);
	
//	============Show Data============
	
	List<StudentEntity> listShow();
	
//	============Delete Data==========
	
	public void DeleteData(long id);
	
//	============UpdateData============
	
	public StudentEntity updateData(Long id);
	public void UpdateData(StudentDto studentDto);

}
