import axios from 'axios';
import React, { useState } from 'react';
import { Dimensions, Text, TouchableOpacity, View, StyleSheet, TextInput } from 'react-native';

const deviceWidth = Dimensions.get('window').width
const deviceHeight = Dimensions.get('window').height
const SERVER_URL = 'https://k5a101.p.ssafy.io/api/v1/'

const StatusTutorial = ({ navigation: { navigate }, route}) => {

  const [inputValue, setInputValue] = useState('')

  const registerStatus = () => {
    userSetting()
  }

  const userSetting = () => {
    setNickname()
  }

  const setNickname = () => {
    axios({
      url: SERVER_URL + 'user/nameSet',
      method: 'post',
      data: {
        userName: route.params.nickname,
        userPK: 2
      }
    })
    .then(() => {
      setEmoji()
    })
    .catch((err) => {
      console.warn(err)
    })
  }

  const setEmoji = () => {
    axios({
      url: SERVER_URL + 'user/emojiSet',
      method: 'post',
      data: {
        userEmoji: route.params.emoji,
        userPK: 2
      }
    })
    .then(() => {
      setStatus()
    })
    .catch((err) => {
      console.warn(err)
    })
  }

  const setStatus = () => {
    axios({
      url: SERVER_URL + 'content/create/status',
      method: 'post',
      data: {
        color: 'pink',
        exon: inputValue,
        userPK: 2
      }
    })
    .then(() => {
      navigate('Main')
      alert('완료')
    })
    .catch((err) => {
      console.warn(err)
    })
  }

  return (
    <View>
      <View style={styles.statusContainer}>
        <View style={{
            width: deviceWidth * 0.8
          }}
        > 
          <Text style={{
            color: '#0B1C26',
            fontSize: 22,
            fontWeight: 'bold',
          }}>
            오늘 내 기분은?
          </Text>
          <Text style={{
            color: '#4F5457',
            fontSize: 14,
            marginTop: deviceHeight * 0.01,
          }}>
            오늘 당신의 기분은 어떠신가요?
          </Text>
        </View>
        <View
          style={styles.statusInput}
        >
          <TextInput
            style={{
              fontSize: 18,
            }}
            autoCapitalize={'none'}
            value={inputValue}
            onChangeText={(e) => setInputValue(e)}
            maxLength={10}
            placeholder='상태 입력'
          />
        </View>
      </View>
      <View style={styles.register__container}>
        <TouchableOpacity
          style={[styles.register, inputValue === '' && { backgroundColor: '#A1A1A1'}]}
          disabled={inputValue === ''}
          onPress={() => registerStatus()}
        >
          <Text
            style={styles.register__text}
          >
            등록
          </Text>
        </TouchableOpacity>
      </View>
    </View>
  )
}

const styles = StyleSheet.create({
  statusContainer: {
    alignItems: 'center',
    marginTop: deviceHeight * 0.15,
  },
  statusInput: {
    borderBottomColor: '#0B1C26',
    borderBottomWidth: 2,
    marginTop: deviceHeight * 0.1,
    paddingHorizontal: 10,
    width: deviceWidth * 0.8,
  },
  register: {
    alignItems: 'center',
    backgroundColor: '#0B1C26',
    borderRadius: 20,
    height: 45,
    justifyContent: 'center',
    marginTop: deviceHeight * 0.35,
    width: deviceWidth * 0.8,
  },
  register__container: {
    alignItems: 'center',
  },
  register__text: {
    color: 'white',
    fontSize: 15,
  }
})

export default StatusTutorial;