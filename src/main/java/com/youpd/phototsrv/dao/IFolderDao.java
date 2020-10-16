package com.youpd.phototsrv.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFolderDao extends JpaRepository<Folder, Long> {

}
