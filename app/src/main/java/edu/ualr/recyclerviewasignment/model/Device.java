package edu.ualr.recyclerviewasignment.model;

import androidx.annotation.NonNull;

import java.util.Date;

/**
 * Created by irconde on 2019-10-04.
 */
public class Device {
    private String deviceId;
    private String name;
    private DeviceType deviceType;
    private Date lastConnection;
    private Device.DeviceStatus deviceStatus;


    public Device.DeviceStatus getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(Device.DeviceStatus deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public Device(@NonNull String deviceId) {
        this.lastConnection = null;
        this.deviceId = deviceId;
        this.name = "";
        this.deviceStatus = DeviceStatus.Linked;
        this.deviceType = DeviceType.Unknown;
    }

    public Device(final Device device) {
        deviceId = device.getDeviceId();
        name = device.getName();
        deviceStatus = device.getDeviceStatus();
        deviceType = device.getDeviceType();
        Date timeStamp = device.getLastConnection();
        if (timeStamp == null) {
            lastConnection = null;
        } else {
            lastConnection = (Date) timeStamp.clone();
        }
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public Date getLastConnection() {
        return this.lastConnection;
    }

    public void setLastConnection(Date timeStamp) {
        this.lastConnection = timeStamp;
    }

    public enum DeviceType {
        Unknown,
        Desktop,
        Laptop,
        Tablet,
        Smartphone,
        SmartTV,
        GameConsole
    }

    public enum DeviceStatus {
        Connected,
        Ready,
        Linked
    }
}
