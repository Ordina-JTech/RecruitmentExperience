export enum FieldType {
  Text = 'text',
  Number = 'number',
  Select = 'select',
}

export interface Option {
  name: number | string;
  value: any;
}

export interface EditField {
  type: FieldType;
  initialValue?: any;
  label: string;
  options?: Option[];
}

export interface EditDialog {
  [index: string]: EditField;
}
