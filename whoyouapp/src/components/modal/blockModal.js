import React from 'react';
import { Text, View, TouchableOpacity } from 'react-native';
import Modal from "react-native-modal";
import { connect } from 'react-redux'
const BlockModal = ({ isModalVisible, setModalVisible, deviceWidth, deviceHeight }) => {
  const sendModalVisible = () => {
    setModalVisible(!isModalVisible)
  }

  const sendReport = () => {
    console.warn('Send Block')
    sendModalVisible()
  }

  return (
    <Modal
      isVisible={isModalVisible}
      onBackdropPress={sendModalVisible}
      useNativeDriver={true}
      style={{
        flex: 1, justifyContent: "center", alignItems: "center",
      }}
    >
      <View style={{
        padding: 20,
        backgroundColor: 'white',
        width: 320,
        height: 180,
      }}>
        <Text style={{
          fontSize: deviceWidth * 0.053,
          // fontSize: 22,
          fontWeight: 'bold',
          marginBottom: 20
        }}>차단하기</Text>
        <Text style={{
          fontSize: deviceWidth * 0.035,
          // fontSize: 14,
          marginBottom: 20
        }}>해당 유저를 차단하겠습니까?</Text>
        <View style={{ paddingTop: 24, flexDirection: 'row', justifyContent: 'flex-end', alignItems: 'center' }}>
          <TouchableOpacity style={{ paddingRight: 30 }} onPress={sendModalVisible}>
            <Text style={{
              fontSize: deviceWidth * 0.038,
              fontSize: 16
            }}>아니오</Text>
          </TouchableOpacity>
          <TouchableOpacity style={{ paddingRight: 20 }} onPress={sendReport}>
            <Text style={{
              fontSize: deviceWidth * 0.038,
              fontSize: 16,
              color: 'red'
            }}>네</Text>
          </TouchableOpacity>
        </View>
      </View>
    </Modal>
  )

};


function mapStateToProps(state) {
  return {
    deviceWidth: state.user.deviceWidth,
    deviceHeight: state.user.deviceHeight,
  }
}


export default connect(mapStateToProps)(BlockModal);