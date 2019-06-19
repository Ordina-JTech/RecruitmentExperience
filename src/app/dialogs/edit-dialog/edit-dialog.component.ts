import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { EditDialog, EditField, FieldType } from 'src/app/definitions/edit-dialog';

@Component({
  selector: 'app-edit-dialog',
  templateUrl: './edit-dialog.component.html',
  styleUrls: ['./edit-dialog.component.scss']
})
export class EditDialogComponent implements OnInit {

  editForm: FormGroup;
  fieldNames: string[];
  fields: EditDialog;

  selectedFiles: any = {};

  constructor(private dialogRef: MatDialogRef<EditDialogComponent>,
              @Inject(MAT_DIALOG_DATA) private data: EditDialog) {}

  ngOnInit() {
    this.fields = this.data;
    this.fieldNames = Object.keys(this.data);

    const formControls = this.fieldNames.reduce((acc, key) => {
      const field = this.data[key];
      return {
        ...acc,
        [key]: new FormControl(this.getInitialValue(field)),
      };
    }, {});

    this.editForm = new FormGroup(formControls);
  }

  getInitialValue(field: EditField): any {
    if (field.initialValue !== undefined) {
      return field.initialValue;
    } else {
      switch (field.type) {
        case FieldType.Number: return 0;
        case FieldType.RichText:
        case FieldType.Text: return '';
        case FieldType.Select: return field.options[0].value;
      }
    }
  }

  handleSelectFile = (fieldName, event: Event) => {
    this.selectedFiles[fieldName] = (event.target as any).files[0];
  }

  submit = (e: Event) => {
    e.preventDefault();
    const result = {
      ...this.editForm.value,
      ...this.selectedFiles,
    };
    this.dialogRef.close(result);
  }
}
