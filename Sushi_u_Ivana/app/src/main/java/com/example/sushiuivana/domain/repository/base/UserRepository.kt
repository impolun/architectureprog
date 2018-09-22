package com.example.sushiuivana.domain.repository.base

import com.example.sushiuivana.domain.entity.base.UserEntity

interface UserRepository<T : UserEntity> : Repository<T>