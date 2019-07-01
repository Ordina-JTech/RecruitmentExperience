export type MessageType = string;

export interface Message {
  type: MessageType;
  payload?: any;
}
