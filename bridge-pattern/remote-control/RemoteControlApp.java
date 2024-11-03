/**
 * Bridge Pattern: Build a "Remote Control" system for devices (TV, audio system) where each device has different controls (volume, channel).
 */

// Implementor Interface
interface Device {
    void powerOn();
    void powerOff();
    void setVolume(int volume);
    void setChannel(int channel);
}

// Concrete Implementors
class TV implements Device {
    @Override
    public void powerOn() {
        System.out.println("TV is powered on.");
    }

    @Override
    public void powerOff() {
        System.out.println("TV is powered off.");
    }

    @Override
    public void setVolume(int volume) {
        System.out.println("TV volume set to " + volume);
    }

    @Override
    public void setChannel(int channel) {
        System.out.println("TV channel set to " + channel);
    }
}

class AudioSystem implements Device {
    @Override
    public void powerOn() {
        System.out.println("Audio System is powered on.");
    }

    @Override
    public void powerOff() {
        System.out.println("Audio System is powered off.");
    }

    @Override
    public void setVolume(int volume) {
        System.out.println("Audio System volume set to " + volume);
    }

    @Override
    public void setChannel(int channel) {
        System.out.println("Audio System channel set to " + channel);
    }
}

// Abstraction
abstract class RemoteControl {
    protected Device device;

    public RemoteControl(Device device) {
        this.device = device;
    }

    abstract void powerOn();
    abstract void powerOff();
    abstract void setVolume(int volume);
    abstract void setChannel(int channel);
}

// Refined Abstraction
class BasicRemoteControl extends RemoteControl {
    public BasicRemoteControl(Device device) {
        super(device);
    }

    @Override
    void powerOn() {
        device.powerOn();
    }

    @Override
    void powerOff() {
        device.powerOff();
    }

    @Override
    void setVolume(int volume) {
        device.setVolume(volume);
    }

    @Override
    void setChannel(int channel) {
        device.setChannel(channel);
    }
}

// Client Code
public class RemoteControlApp {
    public static void main(String[] args) {
        Device tv = new TV();
        RemoteControl tvRemote = new BasicRemoteControl(tv);
        tvRemote.powerOn();
        tvRemote.setVolume(15);
        tvRemote.setChannel(5);
        tvRemote.powerOff();

        System.out.println();

        Device audioSystem = new AudioSystem();
        RemoteControl audioRemote = new BasicRemoteControl(audioSystem);
        audioRemote.powerOn();
        audioRemote.setVolume(10);
        audioRemote.setChannel(3);
        audioRemote.powerOff();
    }
}

/**
Explanation:
1. `Device` is the implementor interface that defines basic methods for device controls like power, volume, and channel.
2. `TV` and `AudioSystem` are concrete implementors that provide specific implementations for these methods.
3. `RemoteControl` is the abstraction that defines high-level operations for the remote control, associating with a `Device`.
4. `BasicRemoteControl` is the refined abstraction that provides concrete implementations for remote control operations.
5. `RemoteControlApp` is the client that uses the remote control to operate different devices in a unified manner.
*/
