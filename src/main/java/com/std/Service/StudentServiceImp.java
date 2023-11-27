package com.std.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.Dto.StudentDto;
import com.std.Entity.StudentEntity;
import com.std.repository.StudentRepository;

@Service
public class StudentServiceImp implements StudentService{
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public void saveData(StudentDto studentDto) {
		StudentEntity studentEntity = this.DtoToEntity(studentDto);
		
		this.studentRepository.save(studentEntity);
		
	}

	@Override
	public StudentEntity DtoToEntity(StudentDto studentDto) {
		StudentEntity studentEntity = new StudentEntity();

		studentEntity.setStdname(studentDto.getStdname());
		studentEntity.setRollno(studentDto.getRollno());
		studentEntity.setAddress(studentDto.getAddress());

		return studentEntity;
	}

	@Override
	public StudentDto EntityToDto(StudentEntity studentEntity) {
		StudentDto studentDto = new StudentDto();

		studentDto.setAddress(studentEntity.getAddress());
		studentDto.setStdname(studentEntity.getStdname());
		studentDto.setRollno(studentEntity.getRollno());

		return studentDto;
	}

	@Override
	public List<StudentEntity> listShow() {
		return studentRepository.findAll();
	}


	@Override
	public void DeleteData(long id) {
		this.studentRepository.deleteById(id);
	}

	@Override
	public StudentEntity updateData(Long id) {
		Optional<StudentEntity> optional = this.studentRepository.findById(id);
		StudentEntity studentEntity = null;
		
		if(optional.isPresent()) {
			studentEntity=optional.get();
		}else {
			throw new RuntimeException("Invalid iD");
		}
		return studentEntity;
	}

	@Override
	public void UpdateData(StudentDto studentDto) {
		StudentEntity studentEntity =  this.updateData(studentDto.getId());
		
		if(studentEntity!=null) {
			studentEntity.setStdname(studentDto.getStdname());
			studentEntity.setAddress(studentDto.getAddress());
			studentEntity.setRollno(studentDto.getRollno());
		}
	}
	
	

	
		
	

}
